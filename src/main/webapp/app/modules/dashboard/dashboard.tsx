import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './dashboard.scss';

const Dashboard = () => {
  const [leads, setLeads] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLeads = async () => {
      try {
        const response = await axios.get('/api/leads'); // JHipster endpoint for 'Lead' entity
        setLeads(response.data);
      } catch (err) {
        setError('Error fetching data');
      } finally {
        setLoading(false);
      }
    };

    fetchLeads();
  }, []);

  return (
    <div className="dashboard-container">
      <h1>User Dashboard</h1>
      <p>Welcome to your dashboard</p>

      {loading && <p>Loading leads...</p>}
      {error && <p className="error">{error}</p>}
      {leads.length > 0 ? (
        <ul className="lead-list">
          {leads.map((lead) => (
            <li key={lead.id}>
              <h2>{lead.name}</h2> {/* Replace 'name' with actual field */}
              <p>{lead.description}</p> {/* Replace 'description' with actual field */}
            </li>
          ))}
        </ul>
      ) : (
        !loading && <p>No leads available.</p>
      )}
    </div>
  );
};

export default Dashboard;
