import { Moment } from 'moment';

export interface ICrpAtr {
  id?: number;
  cenr?: string;
  refint?: string;
  num?: string;
  nobor?: string;
  typenr?: string;
  idAtr?: string;
  demAtr?: Moment;
  numAtr?: string;
  nomRes?: string;
  cpayIso?: string;
  datope?: Moment;
  regt?: string;
  nomEtr?: string;
  nocli?: string;
  catRes?: string;
  ceco?: string;
  cpayEtg?: string;
  natcpt?: string;
  sens?: string;
  devn?: string;
  mdev?: number;
  taux?: number;
  mnat?: number;
  etab?: string;
  nomFic?: string;
  dateArrete?: Moment;
}

export class CrpAtr implements ICrpAtr {
  constructor(
    public id?: number,
    public cenr?: string,
    public refint?: string,
    public num?: string,
    public nobor?: string,
    public typenr?: string,
    public idAtr?: string,
    public demAtr?: Moment,
    public numAtr?: string,
    public nomRes?: string,
    public cpayIso?: string,
    public datope?: Moment,
    public regt?: string,
    public nomEtr?: string,
    public nocli?: string,
    public catRes?: string,
    public ceco?: string,
    public cpayEtg?: string,
    public natcpt?: string,
    public sens?: string,
    public devn?: string,
    public mdev?: number,
    public taux?: number,
    public mnat?: number,
    public etab?: string,
    public nomFic?: string,
    public dateArrete?: Moment
  ) {}
}
