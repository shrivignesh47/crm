import dayjs from 'dayjs';
import { IContacts } from 'app/shared/model/contacts.model';
import { stage } from 'app/shared/model/enumerations/stage.model';

export interface IDeal {
  id?: string;
  deal_name?: string;
  amount?: number;
  closing_date?: dayjs.Dayjs;
  probability?: number | null;
  next_Step?: string | null;
  expected_revenue?: number | null;
  campaign_source?: string | null;
  description_information?: string | null;
  stage?: keyof typeof stage;
  contacts?: IContacts;
}

export const defaultValue: Readonly<IDeal> = {};
