import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './deal.reducer';

export const Deal = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dealList = useAppSelector(state => state.deal.entities);
  const loading = useAppSelector(state => state.deal.loading);
  const totalItems = useAppSelector(state => state.deal.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
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
      <h2 id="deal-heading" data-cy="DealHeading">
        Deals
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/deal/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Deal
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dealList && dealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('deal_name')}>
                  Deal Name <FontAwesomeIcon icon={getSortIconByFieldName('deal_name')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  Amount <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('closing_date')}>
                  Closing Date <FontAwesomeIcon icon={getSortIconByFieldName('closing_date')} />
                </th>
                <th className="hand" onClick={sort('probability')}>
                  Probability <FontAwesomeIcon icon={getSortIconByFieldName('probability')} />
                </th>
                <th className="hand" onClick={sort('next_Step')}>
                  Next Step <FontAwesomeIcon icon={getSortIconByFieldName('next_Step')} />
                </th>
                <th className="hand" onClick={sort('expected_revenue')}>
                  Expected Revenue <FontAwesomeIcon icon={getSortIconByFieldName('expected_revenue')} />
                </th>
                <th className="hand" onClick={sort('campaign_source')}>
                  Campaign Source <FontAwesomeIcon icon={getSortIconByFieldName('campaign_source')} />
                </th>
                <th className="hand" onClick={sort('description_information')}>
                  Description Information <FontAwesomeIcon icon={getSortIconByFieldName('description_information')} />
                </th>
                <th className="hand" onClick={sort('stage')}>
                  Stage <FontAwesomeIcon icon={getSortIconByFieldName('stage')} />
                </th>
                <th>
                  Contacts <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dealList.map((deal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/deal/${deal.id}`} color="link" size="sm">
                      {deal.id}
                    </Button>
                  </td>
                  <td>{deal.deal_name}</td>
                  <td>{deal.amount}</td>
                  <td>{deal.closing_date ? <TextFormat type="date" value={deal.closing_date} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{deal.probability}</td>
                  <td>{deal.next_Step}</td>
                  <td>{deal.expected_revenue}</td>
                  <td>{deal.campaign_source}</td>
                  <td>{deal.description_information}</td>
                  <td>{deal.stage}</td>
                  <td>{deal.contacts ? <Link to={`/contacts/${deal.contacts.id}`}>{deal.contacts.first_name}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/deal/${deal.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/deal/${deal.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/deal/${deal.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
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
          !loading && <div className="alert alert-warning">No Deals found</div>
        )}
      </div>
      {totalItems ? (
        <div className={dealList && dealList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Deal;
