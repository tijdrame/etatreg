import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AcheteurVendeurService } from 'app/entities/acheteur-vendeur/acheteur-vendeur.service';
import { IAcheteurVendeur, AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

describe('Service Tests', () => {
  describe('AcheteurVendeur Service', () => {
    let injector: TestBed;
    let service: AcheteurVendeurService;
    let httpMock: HttpTestingController;
    let elemDefault: IAcheteurVendeur;
    let expectedResult: IAcheteurVendeur | IAcheteurVendeur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AcheteurVendeurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AcheteurVendeur(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AcheteurVendeur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AcheteurVendeur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AcheteurVendeur', () => {
        const returnedFromService = Object.assign(
          {
            codeAcheteurVendeur: 'BBBBBB',
            description: 'BBBBBB',
            codeInterne: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AcheteurVendeur', () => {
        const returnedFromService = Object.assign(
          {
            codeAcheteurVendeur: 'BBBBBB',
            description: 'BBBBBB',
            codeInterne: 'BBBBBB',
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

      it('should delete a AcheteurVendeur', () => {
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
