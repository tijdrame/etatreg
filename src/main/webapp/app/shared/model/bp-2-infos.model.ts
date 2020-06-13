import { Moment } from 'moment';

export interface IBp2Infos {
  id?: number;
  codeIsoDevise?: string;
  actifBilletEtRcai?: number;
  actifMaisonMere?: number;
  actifAuTresor?: number;
  actifClientDeb?: number;
  actifEffesCpte?: number;
  actifEffetEnc?: number;
  passifMaisonMere?: number;
  passifAuTresor?: number;
  passifCliCpteCh?: number;
  passifCptApresEnc?: number;
  dateChargement?: Moment;
  dateDechargement?: Moment;
  passifCliCptVue?: Moment;
}

export class Bp2Infos implements IBp2Infos {
  constructor(
    public id?: number,
    public codeIsoDevise?: string,
    public actifBilletEtRcai?: number,
    public actifMaisonMere?: number,
    public actifAuTresor?: number,
    public actifClientDeb?: number,
    public actifEffesCpte?: number,
    public actifEffetEnc?: number,
    public passifMaisonMere?: number,
    public passifAuTresor?: number,
    public passifCliCpteCh?: number,
    public passifCptApresEnc?: number,
    public dateChargement?: Moment,
    public dateDechargement?: Moment,
    public passifCliCptVue?: Moment
  ) {}
}
