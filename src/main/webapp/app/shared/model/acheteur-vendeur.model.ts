export interface IAcheteurVendeur {
  id?: number;
  codeAcheteurVendeur?: string;
  description?: string;
  codeInterne?: string;
}

export class AcheteurVendeur implements IAcheteurVendeur {
  constructor(public id?: number, public codeAcheteurVendeur?: string, public description?: string, public codeInterne?: string) {}
}
