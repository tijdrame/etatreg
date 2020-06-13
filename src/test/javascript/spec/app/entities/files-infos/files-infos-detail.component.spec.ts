import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { FilesInfosDetailComponent } from 'app/entities/files-infos/files-infos-detail.component';
import { FilesInfos } from 'app/shared/model/files-infos.model';

describe('Component Tests', () => {
  describe('FilesInfos Management Detail Component', () => {
    let comp: FilesInfosDetailComponent;
    let fixture: ComponentFixture<FilesInfosDetailComponent>;
    const route = ({ data: of({ filesInfos: new FilesInfos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [FilesInfosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FilesInfosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FilesInfosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load filesInfos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.filesInfos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
