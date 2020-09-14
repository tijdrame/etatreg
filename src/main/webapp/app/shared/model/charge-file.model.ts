import { Moment } from 'moment';

export interface IChargeFile {
  id?: number;
  nomFic?: string;
  dateCharge?: Moment;
}

export class ChargeFile implements IChargeFile {
  constructor(public id?: number, public nomFic?: string, public dateCharge?: Moment) {}
}
