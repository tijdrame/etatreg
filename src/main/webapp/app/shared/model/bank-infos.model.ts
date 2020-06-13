export interface IBankInfos {
  id?: number;
  codeId?: string;
  sigle?: string;
  raisonSociale?: string;
}

export class BankInfos implements IBankInfos {
  constructor(public id?: number, public codeId?: string, public sigle?: string, public raisonSociale?: string) {}
}
