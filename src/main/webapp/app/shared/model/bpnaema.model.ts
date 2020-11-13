export interface IBpnaema {
  id?: number;
  codeBqe?: string;
  codeCb?: string;
  description?: string;
}

export class Bpnaema implements IBpnaema {
  constructor(public id?: number, public codeBqe?: string, public codeCb?: string, public description?: string) {}
}
