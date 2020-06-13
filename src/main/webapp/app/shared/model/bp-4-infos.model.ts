export interface IBp4Infos {
  id?: number;
  codeIsoPays?: string;
  libellePays?: string;
  mntnosCartes?: number;
  mntCartesEtr?: number;
}

export class Bp4Infos implements IBp4Infos {
  constructor(
    public id?: number,
    public codeIsoPays?: string,
    public libellePays?: string,
    public mntnosCartes?: number,
    public mntCartesEtr?: number
  ) {}
}
