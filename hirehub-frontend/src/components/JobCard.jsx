export default function JobCard({ job }) {
  return (
    <div style={styles.card}>
      <h3>{job.title}</h3>
      <p><b>Company:</b> {job.company}</p>
      <p><b>Location:</b> {job.location}</p>
      <p><b>Salary:</b> ₹{job.salary}</p>
      <p>{job.description}</p>
    </div>
  );
}

const styles = {
  card: {
    border: "1px solid #ddd",
    padding: "12px",
    marginBottom: "10px",
    borderRadius: "6px",
  },
};
