import { IFilesInfos } from 'app/shared/model/files-infos.model';

export interface IChargement {
  id?: number;
  filesInfos?: IFilesInfos;
}

export class Chargement implements IChargement {
  constructor(public id?: number, public filesInfos?: IFilesInfos) {}
}
