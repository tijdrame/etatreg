export interface IBpbqe {
  id?: number;
  codeBqe?: string;
  codeCb?: string;
  description?: string;
}

export class Bpbqe implements IBpbqe {
  constructor(public id?: number, public codeBqe?: string, public codeCb?: string, public description?: string) {}
}
