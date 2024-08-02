import lead from 'app/entities/lead/lead.reducer';
import contacts from 'app/entities/contacts/contacts.reducer';
import accounts from 'app/entities/accounts/accounts.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  lead,
  contacts,
  accounts,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
