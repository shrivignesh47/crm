import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './lead.reducer';

export const LeadDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leadEntity = useAppSelector(state => state.lead.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="leadDetailsHeading">Lead</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{leadEntity.id}</dd>
          <dt>
            <span id="first_name">First Name</span>
          </dt>
          <dd>{leadEntity.first_name}</dd>
          <dt>
            <span id="last_name">Last Name</span>
          </dt>
          <dd>{leadEntity.last_name}</dd>
          <dt>
            <span id="company">Company</span>
          </dt>
          <dd>{leadEntity.company}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{leadEntity.title}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{leadEntity.email}</dd>
          <dt>
            <span id="fax">Fax</span>
          </dt>
          <dd>{leadEntity.fax}</dd>
          <dt>
            <span id="website">Website</span>
          </dt>
          <dd>{leadEntity.website}</dd>
          <dt>
            <span id="lead_source">Lead Source</span>
          </dt>
          <dd>{leadEntity.lead_source}</dd>
          <dt>
            <span id="lead_status">Lead Status</span>
          </dt>
          <dd>{leadEntity.lead_status}</dd>
          <dt>
            <span id="industry">Industry</span>
          </dt>
          <dd>{leadEntity.industry}</dd>
          <dt>
            <span id="no_of_emp">No Of Emp</span>
          </dt>
          <dd>{leadEntity.no_of_emp}</dd>
          <dt>
            <span id="annual_revenue">Annual Revenue</span>
          </dt>
          <dd>{leadEntity.annual_revenue}</dd>
          <dt>
            <span id="rating">Rating</span>
          </dt>
          <dd>{leadEntity.rating}</dd>
          <dt>
            <span id="social_media">Social Media</span>
          </dt>
          <dd>{leadEntity.social_media}</dd>
          <dt>
            <span id="media_handle_id">Media Handle Id</span>
          </dt>
          <dd>{leadEntity.media_handle_id}</dd>
          <dt>
            <span id="street">Street</span>
          </dt>
          <dd>{leadEntity.street}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{leadEntity.city}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{leadEntity.state}</dd>
          <dt>
            <span id="zipcode">Zipcode</span>
          </dt>
          <dd>{leadEntity.zipcode}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{leadEntity.country}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{leadEntity.description}</dd>
          <dt>
            <span id="lead_image">Lead Image</span>
          </dt>
          <dd>
            {leadEntity.lead_image ? (
              <div>
                {leadEntity.lead_imageContentType ? (
                  <a onClick={openFile(leadEntity.lead_imageContentType, leadEntity.lead_image)}>Open&nbsp;</a>
                ) : null}
                <span>
                  {leadEntity.lead_imageContentType}, {byteSize(leadEntity.lead_image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{leadEntity.phone}</dd>
        </dl>
        <Button tag={Link} to="/lead" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/lead/${leadEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default LeadDetail;
