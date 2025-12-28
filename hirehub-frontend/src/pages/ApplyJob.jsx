import { useParams, useNavigate } from "react-router-dom";
import { useState } from "react";
import axiosClient from "../api/axiosClient";

export default function ApplyJob() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [applicant, setApplicant] = useState({
    name: "",
    email: "",
    resumeLink: ""
  });

  const handleChange = (e) => {
    setApplicant({ ...applicant, [e.target.name]: e.target.value });
  };

  const submitApplication = (e) => {
    e.preventDefault();

    axiosClient.post(`/jobs/${id}/apply`, applicant)
      .then(() => {
        alert("Application Submitted Successfully!");
        navigate("/jobs");
      })
      .catch(() => alert("Failed to apply"));
  };

  return (
    <div style={{ width: "60%", margin: "auto", marginTop: 30 }}>
      <h2>Apply for Job</h2>

      <form onSubmit={submitApplication} style={{ display: "flex", flexDirection: "column", gap: 10 }}>
        <input name="name" placeholder="Your Name" onChange={handleChange} required />
        <input name="email" placeholder="Email" onChange={handleChange} required />
        <input name="resumeLink" placeholder="Resume Drive/LinkedIn URL" onChange={handleChange} required />

        <button type="submit">Submit Application</button>
      </form>
    </div>
  );
}
