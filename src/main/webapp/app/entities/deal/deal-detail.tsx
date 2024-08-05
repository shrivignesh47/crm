import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './deal.reducer';

export const DealDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dealEntity = useAppSelector(state => state.deal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dealDetailsHeading">Deal</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{dealEntity.id}</dd>
          <dt>
            <span id="deal_name">Deal Name</span>
          </dt>
          <dd>{dealEntity.deal_name}</dd>
          <dt>
            <span id="amount">Amount</span>
          </dt>
          <dd>{dealEntity.amount}</dd>
          <dt>
            <span id="closing_date">Closing Date</span>
          </dt>
          <dd>{dealEntity.closing_date ? <TextFormat value={dealEntity.closing_date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="probability">Probability</span>
          </dt>
          <dd>{dealEntity.probability}</dd>
          <dt>
            <span id="next_Step">Next Step</span>
          </dt>
          <dd>{dealEntity.next_Step}</dd>
          <dt>
            <span id="expected_revenue">Expected Revenue</span>
          </dt>
          <dd>{dealEntity.expected_revenue}</dd>
          <dt>
            <span id="campaign_source">Campaign Source</span>
          </dt>
          <dd>{dealEntity.campaign_source}</dd>
          <dt>
            <span id="description_information">Description Information</span>
          </dt>
          <dd>{dealEntity.description_information}</dd>
          <dt>
            <span id="stage">Stage</span>
          </dt>
          <dd>{dealEntity.stage}</dd>
          <dt>Contacts</dt>
          <dd>{dealEntity.contacts ? dealEntity.contacts.first_name : ''}</dd>
        </dl>
        <Button tag={Link} to="/deal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/deal/${dealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DealDetail;
