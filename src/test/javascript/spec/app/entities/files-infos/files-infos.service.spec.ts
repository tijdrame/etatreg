import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FilesInfosService } from 'app/entities/files-infos/files-infos.service';
import { IFilesInfos, FilesInfos } from 'app/shared/model/files-infos.model';

describe('Service Tests', () => {
  describe('FilesInfos Service', () => {
    let injector: TestBed;
    let service: FilesInfosService;
    let httpMock: HttpTestingController;
    let elemDefault: IFilesInfos;
    let expectedResult: IFilesInfos | IFilesInfos[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FilesInfosService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FilesInfos(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDernierChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDernierDechargement: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FilesInfos', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDernierChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDernierDechargement: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDernierChargement: currentDate,
            dateDernierDechargement: currentDate,
          },
          returnedFromService
        );

        service.create(new FilesInfos()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FilesInfos', () => {
        const returnedFromService = Object.assign(
          {
            codeFile: 'BBBBBB',
            description: 'BBBBBB',
            inputPath: 'BBBBBB',
            outputPath: 'BBBBBB',
            dateDernierChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDernierDechargement: currentDate.format(DATE_TIME_FORMAT),
            codeApplication: 'BBBBBB',
            codeFormat: 'BBBBBB',
            codeExtension: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDernierChargement: currentDate,
            dateDernierDechargement: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FilesInfos', () => {
        const returnedFromService = Object.assign(
          {
            codeFile: 'BBBBBB',
            description: 'BBBBBB',
            inputPath: 'BBBBBB',
            outputPath: 'BBBBBB',
            dateDernierChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDernierDechargement: currentDate.format(DATE_TIME_FORMAT),
            codeApplication: 'BBBBBB',
            codeFormat: 'BBBBBB',
            codeExtension: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDernierChargement: currentDate,
            dateDernierDechargement: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FilesInfos', () => {
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
