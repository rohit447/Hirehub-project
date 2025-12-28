import { useState } from "react";
import axiosClient from "../api/axiosClient";
import { useNavigate } from "react-router-dom";

export default function CreateJob() {
  const [job, setJob] = useState({
    title: "",
    company: "",
    location: "",
    salary: "",
    description: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setJob({ ...job, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axiosClient.post("/jobs", job)
      .then(() => {
        alert("Job Created Successfully");
        navigate("/jobs");
      })
      .catch(() => alert("Failed to create job"));
  };

  return (
    <div style={styles.container}>
      <h2>Create New Job</h2>

      <form onSubmit={handleSubmit} style={styles.form}>
        <input name="title" placeholder="Job Title" onChange={handleChange} required />
        <input name="company" placeholder="Company" onChange={handleChange} required />
        <input name="location" placeholder="Location" onChange={handleChange} required />
        <input name="salary" type="number" placeholder="Salary" onChange={handleChange} required />
        <textarea name="description" placeholder="Description" onChange={handleChange} required />

        <button type="submit">Create Job</button>
      </form>
    </div>
  );
}

const styles = {
  container: { width: "60%", margin: "auto", marginTop: 30 },
  form: { display: "flex", flexDirection: "column", gap: 10 }
};
