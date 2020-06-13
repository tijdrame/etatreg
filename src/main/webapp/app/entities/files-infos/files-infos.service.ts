import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFilesInfos } from 'app/shared/model/files-infos.model';

type EntityResponseType = HttpResponse<IFilesInfos>;
type EntityArrayResponseType = HttpResponse<IFilesInfos[]>;

@Injectable({ providedIn: 'root' })
export class FilesInfosService {
  public resourceUrl = SERVER_API_URL + 'api/files-infos';

  constructor(protected http: HttpClient) {}

  create(filesInfos: IFilesInfos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(filesInfos);
    return this.http
      .post<IFilesInfos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(filesInfos: IFilesInfos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(filesInfos);
    return this.http
      .put<IFilesInfos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFilesInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFilesInfos[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(filesInfos: IFilesInfos): IFilesInfos {
    const copy: IFilesInfos = Object.assign({}, filesInfos, {
      dateDernierChargement:
        filesInfos.dateDernierChargement && filesInfos.dateDernierChargement.isValid()
          ? filesInfos.dateDernierChargement.toJSON()
          : undefined,
      dateDernierDechargement:
        filesInfos.dateDernierDechargement && filesInfos.dateDernierDechargement.isValid()
          ? filesInfos.dateDernierDechargement.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDernierChargement = res.body.dateDernierChargement ? moment(res.body.dateDernierChargement) : undefined;
      res.body.dateDernierDechargement = res.body.dateDernierDechargement ? moment(res.body.dateDernierDechargement) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((filesInfos: IFilesInfos) => {
        filesInfos.dateDernierChargement = filesInfos.dateDernierChargement ? moment(filesInfos.dateDernierChargement) : undefined;
        filesInfos.dateDernierDechargement = filesInfos.dateDernierDechargement ? moment(filesInfos.dateDernierDechargement) : undefined;
      });
    }
    return res;
  }
}
