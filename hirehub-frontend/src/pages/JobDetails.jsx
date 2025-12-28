import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axiosClient from "../api/axiosClient";

export default function JobDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [job, setJob] = useState(null);

  useEffect(() => {
    axiosClient
      .get(`/jobs/${id}`)
      .then((res) => setJob(res.data))
      .catch(() => console.log("Job not found"));
  }, [id]);

  if (!job) return <h2 style={{ textAlign: "center" }}>Loading...</h2>;

  return (
    <div style={styles.container}>
      <button onClick={() => navigate(-1)} style={styles.backBtn}>
        ⬅ Back
      </button>

      <button
            onClick={() => navigate(`/jobs/${id}/apply`)}
            style={styles.applyBtn}
          >Apply Job
          </button>

      <h1>{job.title}</h1>
      <h3>{job.company}</h3>
      <p><b>Location:</b> {job.location}</p>
      <p><b>Salary:</b> ₹{job.salary}</p>
      <p><b>Description:</b> {job.description}</p>
      <p><b>Posted On:</b> {job.createdAt}</p>
    </div>
  );
}

const styles = {
  container: { width: "70%", margin: "auto", marginTop: "20px" },
  backBtn: {
    padding: "8px 14px",
    border: "none",
    background: "black",
    color: "white",
    cursor: "pointer",
    marginBottom: "15px",
  },
};
