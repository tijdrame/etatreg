import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BduAutorService } from 'app/entities/bdu-autor/bdu-autor.service';
import { IBduAutor, BduAutor } from 'app/shared/model/bdu-autor.model';

describe('Service Tests', () => {
  describe('BduAutor Service', () => {
    let injector: TestBed;
    let service: BduAutorService;
    let httpMock: HttpTestingController;
    let elemDefault: IBduAutor;
    let expectedResult: IBduAutor | IBduAutor[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BduAutorService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BduAutor(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BduAutor', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BduAutor()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BduAutor', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureBeneficiaire: 'BBBBBB',
            paysResidence: 1,
            ville: 1,
            statutJuridique: 1,
            sexeBeneficiaire: 'BBBBBB',
            secteurActivite: 'BBBBBB',
            tailleEntreprise: 'BBBBBB',
            montantMaxAutorise: 1,
            montantMaxUtilise: 1,
            soldeCompte: 1,
            tauxInteret: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BduAutor', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            natureBeneficiaire: 'BBBBBB',
            paysResidence: 1,
            ville: 1,
            statutJuridique: 1,
            sexeBeneficiaire: 'BBBBBB',
            secteurActivite: 'BBBBBB',
            tailleEntreprise: 'BBBBBB',
            montantMaxAutorise: 1,
            montantMaxUtilise: 1,
            soldeCompte: 1,
            tauxInteret: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BduAutor', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
