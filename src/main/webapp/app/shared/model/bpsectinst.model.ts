export interface IBpsectinst {
  id?: number;
  codeBqe?: string;
  codeCb?: string;
  description?: string;
}

export class Bpsectinst implements IBpsectinst {
  constructor(public id?: number, public codeBqe?: string, public codeCb?: string, public description?: string) {}
}
