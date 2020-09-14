import { Moment } from 'moment';

export interface IBp1His {
  id?: number;
  dco?: Moment;
  age?: string;
  dev?: string;
  ncp?: string;
  ope?: string;
  lib?: string;
  mon?: number;
  sen?: string;
  pie?: string;
  ncc?: string;
  uti?: string;
  utf?: string;
  nat?: string;
  nomFic?: string;
  dateArrete?: Moment;
}

export class Bp1His implements IBp1His {
  constructor(
    public id?: number,
    public dco?: Moment,
    public age?: string,
    public dev?: string,
    public ncp?: string,
    public ope?: string,
    public lib?: string,
    public mon?: number,
    public sen?: string,
    public pie?: string,
    public ncc?: string,
    public uti?: string,
    public utf?: string,
    public nat?: string,
    public nomFic?: string,
    public dateArrete?: Moment
  ) {}
}
