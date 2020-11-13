export interface IBpitrs {
  id?: number;
  description?: string;
  codeCb?: string;
  codeBqe?: string;
}

export class Bpitrs implements IBpitrs {
  constructor(public id?: number, public description?: string, public codeCb?: string, public codeBqe?: string) {}
}
