export interface IBppaysiso {
  id?: number;
  codeBqe?: string;
  codeCb?: string;
  description?: string;
}

export class Bppaysiso implements IBppaysiso {
  constructor(public id?: number, public codeBqe?: string, public codeCb?: string, public description?: string) {}
}
