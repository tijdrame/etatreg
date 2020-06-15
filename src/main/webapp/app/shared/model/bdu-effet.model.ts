import { Moment } from 'moment';

export interface IBduEffet {
  id?: number;
  code?: string;
  numEnreg?: string;
  natureDeposant?: number;
  paysResidence?: number;
  ville?: number;
  montantEffet?: number;
  dateEscompte?: Moment;
  dateEcheance?: Moment;
  nbreJours?: number;
  tauxInteret?: number;
  montantCharges?: number;
}

export class BduEffet implements IBduEffet {
  constructor(
    public id?: number,
    public code?: string,
    public numEnreg?: string,
    public natureDeposant?: number,
    public paysResidence?: number,
    public ville?: number,
    public montantEffet?: number,
    public dateEscompte?: Moment,
    public dateEcheance?: Moment,
    public nbreJours?: number,
    public tauxInteret?: number,
    public montantCharges?: number
  ) {}
}
