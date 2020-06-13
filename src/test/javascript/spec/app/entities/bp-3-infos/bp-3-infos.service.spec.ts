import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Bp3InfosService } from 'app/entities/bp-3-infos/bp-3-infos.service';
import { IBp3Infos, Bp3Infos } from 'app/shared/model/bp-3-infos.model';

describe('Service Tests', () => {
  describe('Bp3Infos Service', () => {
    let injector: TestBed;
    let service: Bp3InfosService;
    let httpMock: HttpTestingController;
    let elemDefault: IBp3Infos;
    let expectedResult: IBp3Infos | IBp3Infos[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(Bp3InfosService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Bp3Infos(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bp3Infos', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Bp3Infos()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bp3Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoDevise: 'BBBBBB',
            libelleDevise: 'BBBBBB',
            acheteurVendeur: 'BBBBBB',
            achatsBilletETr: 1,
            ventesBilletEtr: 1,
            achatsChqVoy: 1,
            ventesChqVoy: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bp3Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoDevise: 'BBBBBB',
            libelleDevise: 'BBBBBB',
            acheteurVendeur: 'BBBBBB',
            achatsBilletETr: 1,
            ventesBilletEtr: 1,
            achatsChqVoy: 1,
            ventesChqVoy: 1,
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

      it('should delete a Bp3Infos', () => {
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
