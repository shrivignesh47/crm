import dayjs from 'dayjs';
import { source } from 'app/shared/model/enumerations/source.model';

export interface IContacts {
  id?: string;
  first_name?: string;
  last_name?: string;
  account_name?: string;
  vendor_name?: string;
  lead_source?: keyof typeof source;
  email?: string;
  title?: string;
  phone?: number;
  department?: string;
  mobile?: number;
  fax?: string | null;
  date_of_birth?: dayjs.Dayjs;
  social_media_handle?: string;
  street?: string;
  city?: string;
  state?: string;
  zip?: number;
  country?: string;
  description?: string;
}

export const defaultValue: Readonly<IContacts> = {};
