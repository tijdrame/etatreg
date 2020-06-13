export interface IObjetCredit {
  id?: number;
  codeBdu?: string;
  codeCb?: string;
  description?: string;
}

export class ObjetCredit implements IObjetCredit {
  constructor(public id?: number, public codeBdu?: string, public codeCb?: string, public description?: string) {}
}
