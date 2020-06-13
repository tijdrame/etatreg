export interface ISecteurActivite {
  id?: number;
  codeBdu?: string;
  codeCb?: string;
  description?: string;
}

export class SecteurActivite implements ISecteurActivite {
  constructor(public id?: number, public codeBdu?: string, public codeCb?: string, public description?: string) {}
}
