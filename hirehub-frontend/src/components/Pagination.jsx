export default function Pagination({ page, setPage }) {
  return (
    <div style={{ marginTop: 20 }}>
      <button onClick={() => setPage(page - 1)} disabled={page === 0}>
        Prev
      </button>

      <span style={{ margin: "0 10px" }}>Page: {page + 1}</span>

      <button onClick={() => setPage(page + 1)}>Next</button>
    </div>
  );
}
