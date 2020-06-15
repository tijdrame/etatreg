import { Moment } from 'moment';

export interface IBduCdb {
  id?: number;
  code?: string;
  numEnreg?: string;
  dateTraitement?: Moment;
  typeCredit?: number;
  objetCredit?: number;
  montantCreditDemande?: number;
  dureeCreditDemande?: number;
  tauxInteretSouhaite?: string;
  natureDebiteur?: string;
  paysResidence?: number;
  ville?: number;
  statutJuridique?: number;
  sexeDebiteur?: string;
  secteurActivite?: string;
  tailleEntreprise?: string;
  decision?: number;
  motifRejet?: string;
  dateCredit?: Moment;
  montantCreditAccorde?: number;
  dureeCreditAccorde?: number;
  periodiciteRemboursement?: number;
  tauxInteretApplique?: string;
  montantInteret?: number;
  montantCharges?: number;
  montantCommission?: number;
  autresCommissions?: number;
}

export class BduCdb implements IBduCdb {
  constructor(
    public id?: number,
    public code?: string,
    public numEnreg?: string,
    public dateTraitement?: Moment,
    public typeCredit?: number,
    public objetCredit?: number,
    public montantCreditDemande?: number,
    public dureeCreditDemande?: number,
    public tauxInteretSouhaite?: string,
    public natureDebiteur?: string,
    public paysResidence?: number,
    public ville?: number,
    public statutJuridique?: number,
    public sexeDebiteur?: string,
    public secteurActivite?: string,
    public tailleEntreprise?: string,
    public decision?: number,
    public motifRejet?: string,
    public dateCredit?: Moment,
    public montantCreditAccorde?: number,
    public dureeCreditAccorde?: number,
    public periodiciteRemboursement?: number,
    public tauxInteretApplique?: string,
    public montantInteret?: number,
    public montantCharges?: number,
    public montantCommission?: number,
    public autresCommissions?: number
  ) {}
}
