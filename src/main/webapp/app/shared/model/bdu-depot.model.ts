import { Moment } from 'moment';

export interface IBduDepot {
  id?: number;
  code?: string;
  numEnreg?: string;
  natureDepot?: number;
  paysResidence?: number;
  ville?: number;
  natureDeposant?: number;
  statutJuridique?: number;
  sexeDeposant?: number;
  secteurActivite?: number;
  tailleEntreprise?: number;
  dateDepot?: Moment;
  termeDepot?: number;
  montantDepot?: number;
  tauxRenumeration?: number;
}

export class BduDepot implements IBduDepot {
  constructor(
    public id?: number,
    public code?: string,
    public numEnreg?: string,
    public natureDepot?: number,
    public paysResidence?: number,
    public ville?: number,
    public natureDeposant?: number,
    public statutJuridique?: number,
    public sexeDeposant?: number,
    public secteurActivite?: number,
    public tailleEntreprise?: number,
    public dateDepot?: Moment,
    public termeDepot?: number,
    public montantDepot?: number,
    public tauxRenumeration?: number
  ) {}
}
