import { Moment } from 'moment';

export interface IBp2Com {
  id?: number;
  age?: string;
  dev?: string;
  ncp?: string;
  inti?: string;
  sde?: number;
  cha?: string;
  dou?: Moment;
  ddd?: Moment;
  ddc?: Moment;
  nomFic?: string;
  dateArrete?: Moment;
}

export class Bp2Com implements IBp2Com {
  constructor(
    public id?: number,
    public age?: string,
    public dev?: string,
    public ncp?: string,
    public inti?: string,
    public sde?: number,
    public cha?: string,
    public dou?: Moment,
    public ddd?: Moment,
    public ddc?: Moment,
    public nomFic?: string,
    public dateArrete?: Moment
  ) {}
}
