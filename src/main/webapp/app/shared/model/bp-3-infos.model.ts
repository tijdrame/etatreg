export interface IBp3Infos {
  id?: number;
  codeIsoDevise?: string;
  libelleDevise?: string;
  acheteurVendeur?: string;
  achatsBilletETr?: number;
  ventesBilletEtr?: number;
  achatsChqVoy?: number;
  ventesChqVoy?: number;
}

export class Bp3Infos implements IBp3Infos {
  constructor(
    public id?: number,
    public codeIsoDevise?: string,
    public libelleDevise?: string,
    public acheteurVendeur?: string,
    public achatsBilletETr?: number,
    public ventesBilletEtr?: number,
    public achatsChqVoy?: number,
    public ventesChqVoy?: number
  ) {}
}
