export interface INatureDepot {
  id?: number;
  codeBdu?: string;
  codeCb?: string;
  description?: string;
}

export class NatureDepot implements INatureDepot {
  constructor(public id?: number, public codeBdu?: string, public codeCb?: string, public description?: string) {}
}
