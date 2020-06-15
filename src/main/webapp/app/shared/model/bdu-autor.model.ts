export interface IBduAutor {
  id?: number;
  code?: string;
  numEnreg?: string;
  natureBeneficiaire?: string;
  paysResidence?: number;
  ville?: number;
  statutJuridique?: number;
  sexeBeneficiaire?: string;
  secteurActivite?: string;
  tailleEntreprise?: string;
  montantMaxAutorise?: number;
  montantMaxUtilise?: number;
  soldeCompte?: number;
  tauxInteret?: number;
}

export class BduAutor implements IBduAutor {
  constructor(
    public id?: number,
    public code?: string,
    public numEnreg?: string,
    public natureBeneficiaire?: string,
    public paysResidence?: number,
    public ville?: number,
    public statutJuridique?: number,
    public sexeBeneficiaire?: string,
    public secteurActivite?: string,
    public tailleEntreprise?: string,
    public montantMaxAutorise?: number,
    public montantMaxUtilise?: number,
    public soldeCompte?: number,
    public tauxInteret?: number
  ) {}
}
