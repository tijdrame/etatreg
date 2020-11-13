export interface IBpdevise {
  id?: number;
  codeBqe?: string;
  codeCb?: string;
  description?: string;
}

export class Bpdevise implements IBpdevise {
  constructor(public id?: number, public codeBqe?: string, public codeCb?: string, public description?: string) {}
}
