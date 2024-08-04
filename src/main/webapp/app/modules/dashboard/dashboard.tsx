
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card, CardBody, CardTitle, CardText, Spinner, Alert, Row, Col, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import './dashboard.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Dashboard = () => {
  const [leads, setLeads] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedLead, setSelectedLead] = useState(null);
  const [modal, setModal] = useState(false);

  // Calculate total, new, and closed leads dynamically
  const totalLeads = leads.length;
  const newLeadsCount = leads.filter(lead => lead.lead_status === 'New').length;
  const closedLeadsCount = leads.filter(lead => lead.lead_status === 'Closed').length;

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

  const toggleModal = (lead) => {
    setSelectedLead(lead);
    setModal(!modal);
  };

  const closeLead = async (id) => {
    try {
      await axios.put(`/api/leads/${id}`, { lead_status: 'Closed' }); // Update lead status to Closed
      // Update the state to reflect the change
      const updatedLeads = leads.map(lead => 
        lead.id === id ? { ...lead, lead_status: 'Closed' } : lead
      );
      setLeads(updatedLeads);
    } catch (err) {
      setError('Error updating lead');
    }
  };

  return (
    <div className="dashboard-container">
      <div className="sidebar">
        <h2>CRM Dashboard</h2>
        <ul>
          <li><a href="/dashboard">Dashboard</a></li>
          <li><a href="/lead">Leads</a></li>
          <li><a href="#services">Services</a></li>
          <li><a href="#analytics">Analytics</a></li>
          <li><a href="#settings">Settings</a></li>
        </ul>
      </div>

      <div className="content">
        <h1>User Dashboard</h1>
        <p>Welcome to your dashboard</p>

        <Row>
          <Col md="4">
            <Card>
              <CardBody>
                <CardTitle tag="h5">Total Leads</CardTitle>
                <CardText>{totalLeads}</CardText>
              </CardBody>
            </Card>
          </Col>
          <Col md="4">
            <Card>
              <CardBody>
                <CardTitle tag="h5">New Leads</CardTitle>
                <CardText>{newLeadsCount}</CardText>
              </CardBody>
            </Card>
          </Col>
          <Col md="4">
            <Card>
              <CardBody>
                <CardTitle tag="h5">Closed Leads</CardTitle>
                <CardText>{closedLeadsCount}</CardText>
              </CardBody>
            </Card>
          </Col>
        </Row>

        <h2 id="leads">Leads</h2>

        {loading && <Spinner color="primary" />}
        {error && <Alert color="danger">{error}</Alert>}
        {leads.length > 0 ? (
          <Row className="lead-list">
            {leads.map((lead) => (
              <Col md="6" lg="4" key={lead.id} className="mb-4">
                <Card>
                  <CardBody>
                    <CardTitle tag="h5">{lead.first_name} {lead.last_name}</CardTitle>
                    <CardText><strong>Company:</strong> {lead.company}</CardText>
                    <CardText><strong>Website:</strong> {lead.website}</CardText>
                    <CardText><strong>Email:</strong> {lead.email}</CardText>
                    <CardText><strong>Source:</strong> {lead.lead_source}</CardText>
                    <Button color="primary" size="sm" onClick={() => toggleModal(lead)}>View More</Button>
               
                  </CardBody>
                </Card>
              </Col>
            ))}
          </Row>
        ) : (
          !loading && <p>No leads available.</p>
        )}

        {selectedLead && (
          <Modal isOpen={modal} toggle={toggleModal}>
            <ModalHeader toggle={toggleModal}>Lead Details</ModalHeader>
            <ModalBody>
              <p><strong>Name:</strong> {selectedLead.first_name} {selectedLead.last_name}</p>
              <p><strong>Company:</strong> {selectedLead.company}</p>
              <p><strong>Title:</strong> {selectedLead.title}</p>
              <p><strong>Email:</strong> {selectedLead.email}</p>
              <p><strong>Fax:</strong> {selectedLead.fax}</p>
              <p><strong>Website:</strong> {selectedLead.website}</p>
              <p><strong>Source:</strong> {selectedLead.lead_source}</p>
              <p><strong>Status:</strong> {selectedLead.lead_status}</p>
              <p><strong>Industry:</strong> {selectedLead.industry}</p>
              <p><strong>Employees:</strong> {selectedLead.no_of_emp}</p>
              <p><strong>Annual Revenue:</strong> {selectedLead.annual_revenue}</p>
              <p><strong>Rating:</strong> {selectedLead.rating}</p>
              <p><strong>Social Media:</strong> {selectedLead.social_media}</p>
              <p><strong>Handle ID:</strong> {selectedLead.media_handle_id}</p>
              <p><strong>Address:</strong> {selectedLead.street}, {selectedLead.city}, {selectedLead.state}, {selectedLead.zipcode}, {selectedLead.country}</p>
              <p><strong>Description:</strong> {selectedLead.description}</p>
            </ModalBody>
            <ModalFooter>
              <Button color="secondary" onClick={toggleModal}>Close</Button>
            </ModalFooter>
          </Modal>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
