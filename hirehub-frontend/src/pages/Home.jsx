import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div style={styles.container}>
      <h1 style={styles.title}>HireHub – Scalable Job Posting Platform</h1>

      <p style={styles.subtitle}>
        Find your next opportunity. Search, explore and apply to jobs easily.
      </p>

      <Link to="/jobs">
        <button style={styles.button}>Explore Jobs</button>
      </Link>
    </div>
  );
}

const styles = {
  container: {
    textAlign: "center",
    marginTop: "80px",
  },
  title: {
    fontSize: "34px",
    fontWeight: "bold",
  },
  subtitle: {
    fontSize: "18px",
    marginTop: "10px",
    color: "#444",
  },
  button: {
    marginTop: "25px",
    padding: "10px 18px",
    background: "black",
    color: "white",
    border: "none",
    cursor: "pointer",
    borderRadius: "6px",
  },
};
