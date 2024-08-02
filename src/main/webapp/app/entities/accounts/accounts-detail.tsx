import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './accounts.reducer';

export const AccountsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const accountsEntity = useAppSelector(state => state.accounts.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accountsDetailsHeading">Accounts</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{accountsEntity.id}</dd>
          <dt>
            <span id="account_owner">Account Owner</span>
          </dt>
          <dd>{accountsEntity.account_owner}</dd>
          <dt>
            <span id="rating">Rating</span>
          </dt>
          <dd>{accountsEntity.rating}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{accountsEntity.phone}</dd>
          <dt>
            <span id="account_site">Account Site</span>
          </dt>
          <dd>{accountsEntity.account_site}</dd>
          <dt>
            <span id="fax">Fax</span>
          </dt>
          <dd>{accountsEntity.fax}</dd>
          <dt>
            <span id="website">Website</span>
          </dt>
          <dd>{accountsEntity.website}</dd>
          <dt>
            <span id="account_number">Account Number</span>
          </dt>
          <dd>{accountsEntity.account_number}</dd>
          <dt>
            <span id="ticket_Symbol">Ticket Symbol</span>
          </dt>
          <dd>{accountsEntity.ticket_Symbol}</dd>
          <dt>
            <span id="account_type">Account Type</span>
          </dt>
          <dd>{accountsEntity.account_type}</dd>
          <dt>
            <span id="ownership">Ownership</span>
          </dt>
          <dd>{accountsEntity.ownership}</dd>
          <dt>
            <span id="billing_street">Billing Street</span>
          </dt>
          <dd>{accountsEntity.billing_street}</dd>
          <dt>
            <span id="billing_city">Billing City</span>
          </dt>
          <dd>{accountsEntity.billing_city}</dd>
          <dt>
            <span id="billing_state">Billing State</span>
          </dt>
          <dd>{accountsEntity.billing_state}</dd>
          <dt>
            <span id="billing_code">Billing Code</span>
          </dt>
          <dd>{accountsEntity.billing_code}</dd>
          <dt>
            <span id="billing_country">Billing Country</span>
          </dt>
          <dd>{accountsEntity.billing_country}</dd>
          <dt>
            <span id="shipping_street">Shipping Street</span>
          </dt>
          <dd>{accountsEntity.shipping_street}</dd>
          <dt>
            <span id="shipping_city">Shipping City</span>
          </dt>
          <dd>{accountsEntity.shipping_city}</dd>
          <dt>
            <span id="shipping_state">Shipping State</span>
          </dt>
          <dd>{accountsEntity.shipping_state}</dd>
          <dt>
            <span id="shipping_code">Shipping Code</span>
          </dt>
          <dd>{accountsEntity.shipping_code}</dd>
          <dt>
            <span id="shipping_country">Shipping Country</span>
          </dt>
          <dd>{accountsEntity.shipping_country}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{accountsEntity.description}</dd>
          <dt>
            <span id="employees">Employees</span>
          </dt>
          <dd>{accountsEntity.employees}</dd>
          <dt>
            <span id="sic_code">Sic Code</span>
          </dt>
          <dd>{accountsEntity.sic_code}</dd>
        </dl>
        <Button tag={Link} to="/accounts" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/accounts/${accountsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccountsDetail;
