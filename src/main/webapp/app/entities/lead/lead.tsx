import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, reset } from './lead.reducer';

export const Lead = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );
  const [sorting, setSorting] = useState(false);

  const leadList = useAppSelector(state => state.lead.entities);
  const loading = useAppSelector(state => state.lead.loading);
  const links = useAppSelector(state => state.lead.links);
  const updateSuccess = useAppSelector(state => state.lead.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="lead-heading" data-cy="LeadHeading">
        Leads
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/lead/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Lead
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={leadList ? leadList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {leadList && leadList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    ID <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                  </th>
                  <th className="hand" onClick={sort('first_name')}>
                    First Name <FontAwesomeIcon icon={getSortIconByFieldName('first_name')} />
                  </th>
                  <th className="hand" onClick={sort('last_name')}>
                    Last Name <FontAwesomeIcon icon={getSortIconByFieldName('last_name')} />
                  </th>
                  <th className="hand" onClick={sort('company')}>
                    Company <FontAwesomeIcon icon={getSortIconByFieldName('company')} />
                  </th>
                  <th className="hand" onClick={sort('title')}>
                    Title <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                  </th>
                  <th className="hand" onClick={sort('email')}>
                    Email <FontAwesomeIcon icon={getSortIconByFieldName('email')} />
                  </th>
                  <th className="hand" onClick={sort('fax')}>
                    Fax <FontAwesomeIcon icon={getSortIconByFieldName('fax')} />
                  </th>
                  <th className="hand" onClick={sort('website')}>
                    Website <FontAwesomeIcon icon={getSortIconByFieldName('website')} />
                  </th>
                  <th className="hand" onClick={sort('lead_source')}>
                    Lead Source <FontAwesomeIcon icon={getSortIconByFieldName('lead_source')} />
                  </th>
                  <th className="hand" onClick={sort('lead_status')}>
                    Lead Status <FontAwesomeIcon icon={getSortIconByFieldName('lead_status')} />
                  </th>
                  <th className="hand" onClick={sort('industry')}>
                    Industry <FontAwesomeIcon icon={getSortIconByFieldName('industry')} />
                  </th>
                  <th className="hand" onClick={sort('no_of_emp')}>
                    No Of Emp <FontAwesomeIcon icon={getSortIconByFieldName('no_of_emp')} />
                  </th>
                  <th className="hand" onClick={sort('annual_revenue')}>
                    Annual Revenue <FontAwesomeIcon icon={getSortIconByFieldName('annual_revenue')} />
                  </th>
                  <th className="hand" onClick={sort('rating')}>
                    Rating <FontAwesomeIcon icon={getSortIconByFieldName('rating')} />
                  </th>
                  <th className="hand" onClick={sort('social_media')}>
                    Social Media <FontAwesomeIcon icon={getSortIconByFieldName('social_media')} />
                  </th>
                  <th className="hand" onClick={sort('media_handle_id')}>
                    Media Handle Id <FontAwesomeIcon icon={getSortIconByFieldName('media_handle_id')} />
                  </th>
                  <th className="hand" onClick={sort('street')}>
                    Street <FontAwesomeIcon icon={getSortIconByFieldName('street')} />
                  </th>
                  <th className="hand" onClick={sort('city')}>
                    City <FontAwesomeIcon icon={getSortIconByFieldName('city')} />
                  </th>
                  <th className="hand" onClick={sort('state')}>
                    State <FontAwesomeIcon icon={getSortIconByFieldName('state')} />
                  </th>
                  <th className="hand" onClick={sort('zipcode')}>
                    Zipcode <FontAwesomeIcon icon={getSortIconByFieldName('zipcode')} />
                  </th>
                  <th className="hand" onClick={sort('country')}>
                    Country <FontAwesomeIcon icon={getSortIconByFieldName('country')} />
                  </th>
                  <th className="hand" onClick={sort('description')}>
                    Description <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                  </th>
                  <th className="hand" onClick={sort('lead_image')}>
                    Lead Image <FontAwesomeIcon icon={getSortIconByFieldName('lead_image')} />
                  </th>
                  <th className="hand" onClick={sort('phone')}>
                    Phone <FontAwesomeIcon icon={getSortIconByFieldName('phone')} />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {leadList.map((lead, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/lead/${lead.id}`} color="link" size="sm">
                        {lead.id}
                      </Button>
                    </td>
                    <td>{lead.first_name}</td>
                    <td>{lead.last_name}</td>
                    <td>{lead.company}</td>
                    <td>{lead.title}</td>
                    <td>{lead.email}</td>
                    <td>{lead.fax}</td>
                    <td>{lead.website}</td>
                    <td>{lead.lead_source}</td>
                    <td>{lead.lead_status}</td>
                    <td>{lead.industry}</td>
                    <td>{lead.no_of_emp}</td>
                    <td>{lead.annual_revenue}</td>
                    <td>{lead.rating}</td>
                    <td>{lead.social_media}</td>
                    <td>{lead.media_handle_id}</td>
                    <td>{lead.street}</td>
                    <td>{lead.city}</td>
                    <td>{lead.state}</td>
                    <td>{lead.zipcode}</td>
                    <td>{lead.country}</td>
                    <td>{lead.description}</td>
                    <td>
                      {lead.lead_image ? (
                        <div>
                          {lead.lead_imageContentType ? (
                            <a onClick={openFile(lead.lead_imageContentType, lead.lead_image)}>Open &nbsp;</a>
                          ) : null}
                          <span>
                            {lead.lead_imageContentType}, {byteSize(lead.lead_image)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>{lead.phone}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/lead/${lead.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`/lead/${lead.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button
                          onClick={() => (window.location.href = `/lead/${lead.id}/delete`)}
                          color="danger"
                          size="sm"
                          data-cy="entityDeleteButton"
                        >
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && <div className="alert alert-warning">No Leads found</div>
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Lead;
