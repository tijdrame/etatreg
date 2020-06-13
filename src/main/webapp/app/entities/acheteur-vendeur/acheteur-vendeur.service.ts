import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

type EntityResponseType = HttpResponse<IAcheteurVendeur>;
type EntityArrayResponseType = HttpResponse<IAcheteurVendeur[]>;

@Injectable({ providedIn: 'root' })
export class AcheteurVendeurService {
  public resourceUrl = SERVER_API_URL + 'api/acheteur-vendeurs';

  constructor(protected http: HttpClient) {}

  create(acheteurVendeur: IAcheteurVendeur): Observable<EntityResponseType> {
    return this.http.post<IAcheteurVendeur>(this.resourceUrl, acheteurVendeur, { observe: 'response' });
  }

  update(acheteurVendeur: IAcheteurVendeur): Observable<EntityResponseType> {
    return this.http.put<IAcheteurVendeur>(this.resourceUrl, acheteurVendeur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAcheteurVendeur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAcheteurVendeur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
