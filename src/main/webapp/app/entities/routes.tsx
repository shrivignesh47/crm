import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Lead from './lead';
import Contacts from './contacts';
import Accounts from './accounts';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="lead/*" element={<Lead />} />
        <Route path="contacts/*" element={<Contacts />} />
        <Route path="accounts/*" element={<Accounts />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
