import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home";
import Jobs from "../pages/Jobs";
import JobDetails from "../pages/JobDetails";
import CreateJob from "../pages/CreateJob";
import ApplyJob from "../pages/ApplyJob";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/jobs" element={<Jobs />} />
      <Route path="/jobs/:id" element={<JobDetails />} />
      <Route path="/create-job" element={<CreateJob />} />
      <Route path="/jobs/:id/apply" element={<ApplyJob />} />
    </Routes>
  );
}
