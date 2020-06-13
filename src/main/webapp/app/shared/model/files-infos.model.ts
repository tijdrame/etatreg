import { Moment } from 'moment';

export interface IFilesInfos {
  id?: number;
  codeFile?: string;
  description?: string;
  inputPath?: string;
  outputPath?: string;
  dateDernierChargement?: Moment;
  dateDernierDechargement?: Moment;
}

export class FilesInfos implements IFilesInfos {
  constructor(
    public id?: number,
    public codeFile?: string,
    public description?: string,
    public inputPath?: string,
    public outputPath?: string,
    public dateDernierChargement?: Moment,
    public dateDernierDechargement?: Moment
  ) {}
}
