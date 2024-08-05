import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IContacts } from 'app/shared/model/contacts.model';
import { ILead } from 'app/shared/model/lead.model';
import { location } from 'app/shared/model/enumerations/location.model';
import { participants } from 'app/shared/model/enumerations/participants.model';

export interface IMeeting {
  id?: string;
  title?: string;
  location?: keyof typeof location;
  offline_location?: string;
  current_time?: dayjs.Dayjs;
  from?: dayjs.Dayjs;
  to?: dayjs.Dayjs;
  participants?: keyof typeof participants;
  user?: IUser;
  contacts?: IContacts | null;
  lead?: ILead | null;
}

export const defaultValue: Readonly<IMeeting> = {};
