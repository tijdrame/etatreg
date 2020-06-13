export interface INatureDebiteur {
  id?: number;
  codeBdu?: string;
  codeCb?: string;
  description?: string;
}

export class NatureDebiteur implements INatureDebiteur {
  constructor(public id?: number, public codeBdu?: string, public codeCb?: string, public description?: string) {}
}
