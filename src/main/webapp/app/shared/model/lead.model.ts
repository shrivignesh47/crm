import { source } from 'app/shared/model/enumerations/source.model';
import { status } from 'app/shared/model/enumerations/status.model';
import { industry } from 'app/shared/model/enumerations/industry.model';
import { rating } from 'app/shared/model/enumerations/rating.model';
import { social } from 'app/shared/model/enumerations/social.model';

export interface ILead {
  id?: string;
  first_name?: string;
  last_name?: string;
  company?: string;
  title?: string;
  email?: string;
  fax?: string | null;
  website?: string;
  lead_source?: keyof typeof source;
  lead_status?: keyof typeof status;
  industry?: keyof typeof industry;
  no_of_emp?: number;
  annual_revenue?: number | null;
  rating?: keyof typeof rating;
  social_media?: keyof typeof social | null;
  media_handle_id?: string | null;
  street?: string;
  city?: string;
  state?: string;
  zipcode?: number;
  country?: string;
  description?: string | null;
  lead_imageContentType?: string | null;
  lead_image?: string | null;
  phone?: number;
}

export const defaultValue: Readonly<ILead> = {};
