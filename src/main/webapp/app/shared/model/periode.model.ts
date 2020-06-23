export interface IPeriode {
  id?: number;
  code?: string;
  libelle?: string;
}

export class Periode implements IPeriode {
  constructor(public id?: number, public code?: string, public libelle?: string) {}
}
