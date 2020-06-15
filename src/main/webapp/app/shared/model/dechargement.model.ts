export interface IDechargement {
  id?: number;
}

export class Dechargement implements IDechargement {
  constructor(public id?: number) {}
}
