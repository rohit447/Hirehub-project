import { useEffect, useState } from "react";
import axiosClient from "../api/axiosClient";
import JobCard from "../components/JobCard";
import { useNavigate } from "react-router-dom";

export default function Jobs() {
  const [jobs, setJobs] = useState([]);
  const [page, setPage] = useState(0);
  const [search, setSearch] = useState("");
  const [sort, setSort] = useState("");
  const [location, setLocation] = useState("");
  const [totalPages, setTotalPages] = useState(0);

  const navigate = useNavigate();

  // Load jobs when page, sort, or location changes
  useEffect(() => {
    loadJobs();
  }, [page, sort, location]);

  const loadJobs = () => {
    let url = `/jobs?page=${page}&size=5`;

    if (search) url += `&search=${encodeURIComponent(search)}`;
    if (sort) url += `&sort=${sort}`;
    if (location) url += `&location=${encodeURIComponent(location)}`;

    axiosClient
      .get(url)
      .then((res) => {
        setJobs(res.data.content);
        setTotalPages(res.data.totalPages);
      })
      .catch(() => console.log("Failed to fetch jobs"));
  };

  const handleSearch = () => {
    setPage(0);
    loadJobs();
  };

  return (
    <div style={styles.container}>
      <h2>Available Jobs</h2>

      {/* 🔍 Search + Sort + Location */}
      <div style={styles.row}>
        <input
          type="text"
          placeholder="Search by job title..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={styles.input}
        />
        <button onClick={handleSearch} style={styles.button}>
          Search
        </button>

        <select
          onChange={(e) => {
            setSort(e.target.value);
            setPage(0);
          }}
          style={styles.select}
        >
          <option value="">Sort By</option>
          <option value="salaryDesc">Salary High → Low</option>
          <option value="salaryAsc">Salary Low → High</option>
          <option value="recent">Latest Jobs</option>
        </select>

        <select
          onChange={(e) => {
            setLocation(e.target.value);
            setPage(0);
          }}
          style={styles.select}
        >
          <option value="">All Locations</option>
          <option value="Hyderabad">Hyderabad</option>
          <option value="Bangalore">Bangalore</option>
          <option value="Pune">Pune</option>
          <option value="Chennai">Chennai</option>
          <option value="Delhi">Delhi</option>
        </select>
      </div>

      {/* 🧾 Jobs List */}
      {jobs.length === 0 && <p>No jobs found</p>}

      {jobs.map((job) => (
        <div
          key={job.id}
          onClick={() => navigate(`/jobs/${job.id}`)}
          style={{ cursor: "pointer" }}
        >
          <JobCard job={job} />
        </div>
      ))}

      {/* ⏩ Pagination */}
      <div style={styles.pagination}>
        <button onClick={() => setPage(page - 1)} disabled={page === 0}>
          Prev
        </button>

        <span>
          Page {page + 1} of {totalPages}
        </span>

        <button
          onClick={() => setPage(page + 1)}
          disabled={page + 1 === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
}

const styles = {
  container: { width: "70%", margin: "auto", marginTop: "20px" },
  row: { display: "flex", gap: "10px", marginBottom: "20px" },
  input: { padding: "8px", width: "220px" },
  select: { padding: "8px" },
  button: {
    padding: "8px 15px",
    background: "black",
    color: "white",
    border: "none",
    cursor: "pointer",
  },
  pagination: { marginTop: "25px", display: "flex", gap: "10px" },
};
