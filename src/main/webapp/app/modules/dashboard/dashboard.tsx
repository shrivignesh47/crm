import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card, CardBody, CardTitle, CardText, Spinner, Alert, Row, Col, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import './dashboard.scss';

const Dashboard = () => {
  const [leads, setLeads] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedLead, setSelectedLead] = useState(null);
  const [modal, setModal] = useState(false);

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

  return (
    <div className="dashboard-container">
      <h1>User Dashboard</h1>
      <p>Welcome to your dashboard</p>

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
                  <Button color="primary" onClick={() => toggleModal(lead)}>View More</Button>
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
  );
};

export default Dashboard;
